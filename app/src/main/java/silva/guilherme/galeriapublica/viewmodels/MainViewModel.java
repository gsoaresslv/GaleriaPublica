package silva.guilherme.galeriapublica.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import kotlinx.coroutines.CoroutineScope;
import silva.guilherme.galeriapublica.R;
import silva.guilherme.galeriapublica.classes.GalleryPagingSource;
import silva.guilherme.galeriapublica.classes.GalleryRepository;
import silva.guilherme.galeriapublica.classes.ImageData;

public class MainViewModel extends AndroidViewModel {
    int navigationOpSelected = R.id.gridViewOp;

    public int getNavigationOpSelected(){
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int itemId){
        this.navigationOpSelected = navigationOpSelected;
    }
    LiveData<PagingData<ImageData>> pageLv;

    public MainViewModel(@NonNull Application application) {
        super(application);
        GalleryRepository galleryRepository = new GalleryRepository(application);
        GalleryPagingSource galleryPagingSource = new GalleryPagingSource(galleryRepository);
        Pager<Integer, ImageData> pager = new Pager(new PagingConfig(10), () -> galleryPagingSource);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        pageLv = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }
    public LiveData<PagingData<ImageData>> getPageLv() {
        return pageLv;
    }
}
