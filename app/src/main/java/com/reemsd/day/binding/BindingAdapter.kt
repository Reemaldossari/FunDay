package com.reemsd.day.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reemsd.day.R
import com.reemsd.day.home.ApiStatus
import com.reemsd.day.home.PlacesAdapter
import com.reemsd.day.network.PlanData
import com.reemsd.day.network.TopPlaces
import com.reemsd.day.plan.UserPlanAdapter

// get data and convert from link to image .
@BindingAdapter("imageUrl")
 fun bindingImage(imageView: ImageView , imgUrl : String?){
  imgUrl?.let {
      Glide
          .with(imageView.context) // we need context to draw image on screen.
          .load("${imgUrl}") // this function will load image from the link
          .placeholder(R.drawable.loading_animation)  //Before you see the result of the display of the image.
          .error(R.drawable.ic_broken_image) //If the picture is ruined or there are problems,display.
          .into(imageView) //

  }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<TopPlaces>?){
    if(recyclerView.adapter == null){
        recyclerView.adapter = PlacesAdapter()
    }
    val adapter = recyclerView.adapter as PlacesAdapter
    adapter.submitList(data)
}
@BindingAdapter("userPlan")
fun bindUserPlan(recyclerView: RecyclerView, data: List<PlanData>?){
    val UserplanAdapter = recyclerView.adapter as UserPlanAdapter
    UserplanAdapter.submitList(data)
}
@BindingAdapter("apiStatus")
fun ImageView.bindStatus(status: ApiStatus){
    when (status){

        ApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }

        ApiStatus.DONE -> {
            this.visibility = View.GONE

        }

    }


}