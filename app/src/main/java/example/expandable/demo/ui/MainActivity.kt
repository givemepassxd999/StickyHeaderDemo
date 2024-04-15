package example.expandable.demo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.expandable.demo.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private var binding: ActivityMainBinding? = null
    private val dataAdapter = MainListAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerList.apply {
                adapter = dataAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                val sectionItemDecoration = HeaderItemDecoration(object :
                    HeaderItemDecoration.SectionCallback {
                    override fun isHeader(position: Int): Boolean {
                        return dataAdapter.isHeader(position)
                    }

                    override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                        return dataAdapter.getHeaderView(list, position)
                    }

                })
                addItemDecoration(sectionItemDecoration)
            }
            dataAdapter.submitList(viewModel.getDemoList())
        }
    }
}