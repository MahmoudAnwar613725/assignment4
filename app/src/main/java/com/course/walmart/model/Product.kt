package com.course.walmart.model

import java.io.Serializable

data class Product (
    var title: String,
    var price: Double,
    var color: String,
    var image: String,
    var itemId: String,
    var desc: String
): Serializable {

}
