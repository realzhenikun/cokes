package com.example.aninterface

import android.os.Parcel
import android.os.Parcelable
/*
    up主:up主名字,头像,粉丝数,动态
    手动实现 Parcelable 接口
    添加了 writeToParcel()、describeContents() 和 CREATOR 伴生对象
    在 writeToParcel() 方法中，将每个属性写入 Parcel 对象
    在 CREATOR 伴生对象中，提供了 createFromParcel() 和 newArray() 方法用于创建 Up 对象的实例
 */
data class Up(var name: String, var image: Int, var fans: Int, var post: Post) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Post::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeInt(fans)
        parcel.writeParcelable(post, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Up> {
        override fun createFromParcel(parcel: Parcel): Up {
            return Up(parcel)
        }

        override fun newArray(size: Int): Array<Up?> {
            return arrayOfNulls(size)
        }
    }
}