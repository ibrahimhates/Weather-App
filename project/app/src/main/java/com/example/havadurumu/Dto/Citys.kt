package com.example.havadurumu.Dto

import com.example.havadurumu.api.models.MyWeather

object Citys {
    var city : String = ""
    val citys = arrayOf("adana", "adiyaman", "afyonkarahisar", "agri", "aksaray", "amasya", "ankara",
        "antalya", "ardahan", "artvin", "aydin", "balikesir", "bartin", "batman", "bayburt", "bilecik",
        "bingol", "bitlis", "bolu", "burdur", "bursa", "canakkale", "cankiri", "corum", "denizli", "diyarbakir",
        "duzce", "edirne", "elazig", "erzincan", "erzurum", "eskisehir", "gaziantep", "giresun", "gumushane",
        "hakkari", "hatay", "igdir", "isparta", "istanbul", "izmir", "kahramanmaras", "karabuk", "karaman",
        "kars", "kastamonu", "kayseri", "kilis", "kirikkale", "kirklareli", "kirsehir", "kocaeli", "konya",
        "kutahya", "malatya", "manisa", "mardin", "mersin", "mugla", "mus", "nevsehir", "nigde", "ordu",
        "osmaniye", "rize", "sakarya", "samsun", "sanliurfa", "siirt", "sinop", "sivas", "sirnak", "tekirdag",
        "tokat", "trabzon", "tunceli", "usak", "van", "yalova", "yozgat", "zonguldak")
    var myWeather: MyWeather? = null
    var position : Int = 0
}