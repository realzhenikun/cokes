package com.example.aninterface

class UpSender {
    private val upList: MutableList<Up> = mutableListOf()
    private val postList: MutableList<Post> = mutableListOf()

    //创建一个postList,创建upList时需要用到postList
    fun advance() {
        postList.add(Post("......", R.drawable.img_post_1))
        postList.add(Post("好大的风", R.drawable.img_post_2))
        postList.add(Post("好看", R.drawable.img_post_3))
        postList.add(Post("风景", R.drawable.img_post_4))
        postList.add(Post("哇", R.drawable.img_post_5))
        postList.add(Post("111111", R.drawable.img_post_6))
        postList.add(Post(">_<>_<>_<", R.drawable.img_post_7))
    }

    fun createPostList(): MutableList<Post> {
        advance()
        return postList
    }

    fun createUpList(): MutableList<Up> {
        advance()
        upList.add(Up("black", R.drawable.img_1, 999, postList[0]))
        upList.add(Up("white", R.drawable.img_2, 1234, postList[1]))
        upList.add(Up("Tom", R.drawable.img_3, 5678, postList[2]))
        upList.add(Up("Jack", R.drawable.img_4, 1, postList[3]))
        upList.add(Up("987", R.drawable.img_5, 894589, postList[4]))
        upList.add(Up(">_<", R.drawable.img_6, 455, postList[5]))
        upList.add(Up("up77", R.drawable.img_7, 1988, postList[6]))
        return upList
    }

    /*
        将upList与postList关联到一起
        使up与他的post一一对应
     */
    fun connect(upList: MutableList<Up>,postList: MutableList<Post>) {
        postList[0].up = upList[0]
        postList[1].up = upList[1]
        postList[2].up = upList[2]
        postList[3].up = upList[3]
        postList[4].up = upList[4]
        postList[5].up = upList[5]
        postList[6].up = upList[6]
    }
}