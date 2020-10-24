package com.example.apiblog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val api by lazy { retrofit.create(BlogServices::class.java) }
    val blogItems = MutableLiveData<List<Posts>>()

    fun getBlogData() {
        viewModelScope.launch(Dispatchers.Default) {
            //старница и колличество записей
            api.getCurrentBlog(1, 12).posts.map {
                Posts(
                    title = Jsoup.parse(it.title_plain).text(),
                    content = Jsoup.parse(it.content).text()
                )
            }.let {
                blogItems.postValue(it)
            }
        }
    }

    companion object {
        val moshi by lazy {
            Moshi.Builder()
                .build()
        }
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.xda-developers.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}