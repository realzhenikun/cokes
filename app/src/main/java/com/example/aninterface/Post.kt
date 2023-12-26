package com.example.aninterface

import android.os.Parcel
import android.os.Parcelable
/*
    动态:动态简介,动态图片
    手动实现 Parcelable 接口
    添加了 writeToParcel()、describeContents() 和 CREATOR 伴生对象
    在 writeToParcel() 方法中，将每个属性写入 Parcel 对象
    在 CREATOR 伴生对象中，提供了 createFromParcel() 和 newArray() 方法用于创建 Post 对象的实例
 */
data class Post(var introduction: String, var image: Int) : Parcelable {
    lateinit var up: Up

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(introduction)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}
