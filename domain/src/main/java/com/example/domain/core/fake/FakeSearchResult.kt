package com.example.domain.core.fake

import com.example.domain.location.model.Location
import com.example.domain.shop.model.Budget
import com.example.domain.shop.model.BudgetCode
import com.example.domain.shop.model.Coupon
import com.example.domain.shop.model.Genre
import com.example.domain.shop.model.GenreCode
import com.example.domain.shop.model.Mobile
import com.example.domain.shop.model.Photo
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.model.Urls

fun fakeSearchResult(): SearchResult = SearchResult(
    1,
    1,
    1,
    Shop(
        id = ShopId("J001260340"),
        name = "新宿 檸檬 レモン 新宿店",
        nameKana = "きゅうしゅうりょうりせんもんてんしんじゅくれもんしんじゅくてん",
        logoImage = "https://imgfp.hotp.jp/IMGH/24/10/P037732410/P037732410_69.jpg",
        address = "東京都新宿区西新宿１-14-6　西勢ビル3F",
        stationName = "新宿",
        location = Location(
            latitude = 35.6885806402,
            longitude = 139.6970204296,
        ),
        genre = Genre(
            code = GenreCode("G001"),
            name = "居酒屋",
            catch = "個室で味わう北海道の\\\"肉\\\"に\\\"魚\\\"に酒！"
        ),
        budget = Budget(
            code = BudgetCode("B008"),
            name = "4001～5000円",
            average = "000円～5000円",
        ),
        budgetMemo = "付き出し300円（税込）",
        capacity = "75",
        catch = "イタリアン×パーティ♪ 厳選食材使用の窯焼ピザ！",
        mobileAccess = "大阪の上本町駅､鶴橋駅､谷町九丁目駅より徒歩圏内",
        urls = Urls(
            pc = "https://www.hotpepper.jp/strJ001260340/?vos=nhppalsa000016"
        ),
        photo = Photo(
            mobile = Mobile(
                l = "https://imgfp.hotp.jp/IMGH/24/12/P037732412/P037732412_168.jpg",
                s = "https://imgfp.hotp.jp/IMGH/24/12/P037732412/P037732412_100.jpg"
            )
        ),
        open = "月～日、祝日、祝前日: 12:00～翌0:00 （料理L.O. 23:00 ドリンクL.O. 23:00）",
        close = "なし",
        coupon = Coupon.Url("https://www.hotpepper.jp/strJ001260434/scoupon/?vos=nhppalsa000016"),
    ).let { listOf(it.copy(), it.copy(), it.copy(), it.copy(), it.copy()) },
)
