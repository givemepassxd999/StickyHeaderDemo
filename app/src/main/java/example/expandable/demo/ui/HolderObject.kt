package example.expandable.demo.ui

sealed class HolderObject(val type: Int) {

    class Header : HolderObject(TYPE_HEADER)
    class Top : HolderObject(TYPE_TOP)
    class Item(val content: String) : HolderObject(TYPE_ITEM)

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_TOP = 1
        const val TYPE_ITEM = 2
    }
}