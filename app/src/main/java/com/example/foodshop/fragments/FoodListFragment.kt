package com.example.foodshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.R
import com.example.foodshop.adapter.FoodAdapter
import com.example.foodshop.utils.Constants

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_CATEGORY = "category"
class FoodListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var recyclerView: RecyclerView
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category =it.getString(ARG_CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        recyclerView = view.findViewById(R.id.rv_food_list)
        setupRecyclerView()
        return view
    }

    private fun setupRecyclerView(){
        val allFoods = Constants.getAllFoodItems()
        val filteredFoods = if (category != null)
            {
            allFoods.filter {it.category == category}
            }else {
                allFoods
            }
        val foodAdapter = FoodAdapter(filteredFoods){
            (activity as? OnCartUpdateListener)?.onCartUpdate()
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = foodAdapter
    }

    interface OnCartUpdateListener {
        fun onCartUpdate()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category: String) =
            FoodListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
    }
}