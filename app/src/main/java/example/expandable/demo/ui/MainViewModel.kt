package example.expandable.demo.ui

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getDemoList(): List<HolderObject> {
        val list = arrayListOf<HolderObject>()
        for (i in 0..1000) {
            if (i == 0) {
                list.add(HolderObject.Header())
            } else if (i % 10 == 0) {
                list.add(HolderObject.Top())
            } else {
                list.add(HolderObject.Item("測試資料$i"))
            }
        }
        return list
    }
}