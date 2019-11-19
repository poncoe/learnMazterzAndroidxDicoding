package id.poncoe.latihanmasterandroidponcoe.kotlin.recyclerview

object DataKucing {
    private var data = arrayOf(
            arrayOf(
                    "MANX",
                    "Kucing Manx adalah kucing yang berasal dari Pulau Manx. Sebagian orang menyebutkan kucing ini dengan sebutan Rumpy. Manx memiliki ekor yang pendek dan warna bulu terdiri dari cokelat dan lavender. Sifat dari kucing ini adalah setia, ramah dan pintar.",
                    "https://api.luckytruedev.com/kip/j/kucingmanx.jpg"
            ),
            arrayOf(
                    "MAINE COON",
                    "Kucing Maine Coon merupakan ras kucing yang berasal dari Maine, di Amerika Serikat. Kucing ini merupakan keturunan dari ras kucing Anggora dan American Shorthair. Sifat kucing ini adalah lucu, pemalu, dan mudah akrab. Bulunya tipis, lembut, dan terdapat beragam warna.",
                    "https://api.luckytruedev.com/kip/j/kucingmaniecoon.jpg"
            ),
            arrayOf(
                    "BRITISH SHORTHAIR",
                    "Kucing jenis ini dikembangkan di Inggris. Kucing ini adalah jenis kucing yang tenang, lembut, dan pintar. Warna bulu ras kucing ini diantaranya adalah polos (putih, hitam, biru dan krem), dua warna, hitam pekat, dan belang.",
                    "https://api.luckytruedev.com/kip/j/kucingbritish.jpg"
            ),
            arrayOf(
                    "BURMESE",
                    "Kucing Burmese adalah ras kucing yang dibiakan oleh Dr. Joseph Thompson di Amerika Serikat pada tahun sekitar 1930-an. Warna bulu pada ras kucing ini adalah cokelat musang, biru (abu – abu), champagne, lifa, merah, cokelat, dan tortoiseshell. Sifat kucing ini adalah periang dan lucu.",
                    "https://api.luckytruedev.com/kip/j/kucingburmese.jpg"
            ),
            arrayOf(
                    "CHINCHILLA LONGHAIR",
                    "Kucing Chinchilla Longhair adalah kucing  yang berasal dari Inggris. Ras kucing ini adalah kucing yang anggun. Ras ini dibagi dalam dua macam, Chinchilla warna cerah (sejati) dan yang agak gelap (perak gradasi).",
                    "https://api.luckytruedev.com/kip/j/kucingchincilla.jpg"
            ),
            arrayOf(
                    "TONKINESE",
                    "Kucing Tonkinese adalah Ras yang mendapatkan status untuk kontes dari Canadia Cat Association pada tahun 1971. Baru pada tahun 1979 CFA dan TICA mengakui ras ini untuk diikutkan dalam kontes. Pada akhir 1960-an Margaret Conroy tidak lagi mengembangbiakkan tonkinese, tetapi kucing – kucing yang dihasilkannya telah menyebar ke  Amerika Serikat. Pada tahu 1965, seorang breeder Amerika bernama Jane Barletta mulai mengembangkan kucing tonkinese ini.",
                    "https://api.luckytruedev.com/kip/j/kucingtokinese.jpg"
            ),
            arrayOf(
                    "SOMALI",
                    "Kucing Somali adalah kucing versi “semi long hair” dari abyssinian. Sekitar tahun 1920 sampai dengan 1930-an, dari sebuah program pengembangbiakan, versi ini muncul secara spontan. Beberapa dari anak kucing abyssinian tersebut mempunyai ekor dengan bulu tebal, mengembang seperti sikat dan bulu yang panjang menutupi seluruh tubuh.",
                    "https://api.luckytruedev.com/kip/j/kucingsomali.jpg"
            ),
            arrayOf(
                    "PERSIA",
                    "Kucing Persia merupakan kucing yang sangat indah dan cantik dengan bentuk tubuh yang besar, padat, kepala besar dan bulat, ditutupi lapisan bulu yang cukup tebal. Orang awam pun pastin langsung bisa mengenali kucing persia. Di Indonesia, kucing ras persia cukup banyak dikembangbiakkan dibandingkan dengan ras lain. Mungkin bisa disebabkan bulu yang panjang dan tebal serta sifat tenang, anggun dan manja yang merupakan salah satu ciri khas kucing ras persia. Persia lebih mudah dikandangkan, relatif tidak berisik dan lebih cocok hidup di dalam rumah.",
                    "https://api.luckytruedev.com/kip/j/kucingpersia.jpg"
            ),
            arrayOf(
                    "MUNCHKIN",
                    "Kucing Munchkin memiliki banyak catatan sejarah yang menunjukan kucing dengan kaki pendek. Salah satunya di tahun 1944, seorang dokter hewan bernama H.E. Williams mendeskripsikan empat generasi kucing dengan kaki pendek yang hidup liar di Inggris. Kucing – kucing tersebut hidup dengan sehat dan bergerak seperti musang. Sayangnya kucing – kucing ini menghilang pada perang dunia ke dua.",
                    "https://api.luckytruedev.com/kip/j/kucingmunchkin.jpg"
            ),
            arrayOf(
                    "RAS KUCING ASLI INDONESIA",
                    "Kucing Asli Indonesia tidak semua orang Indonesia menyadari bahwa negaranya adalah salah satu negara dengan tingkat keanekaragaman hayati yang sangat tinggi. Padahal beberapa spesies flora dan fauna baru banyak ditemukan di negara kita. Begitu pula kucing, sifat geografi negara kepulauan menyebabkan ada sekumpulan kucing yang berkembang biak disebuah pulau kecil.",
                    "https://api.luckytruedev.com/kip/j/kucingkampung.jpg"
            )
    )
    val listData: ArrayList<Kucing>
        get() {
            val list = ArrayList<Kucing>()
            for (aData in data) {
                val kucing = Kucing()
                kucing.name = aData[0]
                kucing.from = aData[1]
                kucing.photo = aData[2]
                list.add(kucing)
            }
            return list
        }
}