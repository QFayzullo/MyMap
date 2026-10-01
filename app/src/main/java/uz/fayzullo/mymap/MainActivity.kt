package uz.fayzullo.mymap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import uz.fayzullo.mymap.ui.theme.MyMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMapTheme {
                Greeting()
            }
        }
    }
}
// AIzaSyCMY2_CfL3WTvGB6qQ3uXH3BEqnCiq0cfE
// 6C:BF:7B:2F:92:AD:E9:4D:CD:51:48:0F:9B:BD:AE:8C:FD:89:39:142

data class MyClusterItem(
    private val itemPosition: LatLng,
    private val itemTitle: String,
    private val itemSnippet: String
) : ClusterItem {
    override fun getPosition(): LatLng = itemPosition
    override fun getTitle(): String = itemTitle
    override fun getSnippet(): String = itemSnippet
    override fun getZIndex(): Float? = null
}
@Composable
fun Greeting() {


    val cameraPosition = rememberCameraPositionState {
        position =
            CameraPosition.fromLatLngZoom(LatLng(41.204392, 69.232672), 15f)
    }
    var marker = rememberMarkerState("Index", LatLng(41.201638, 69.231998))

    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true
            )
        )
    }
    var properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }

    val clusterItems = remember {
        listOf(
            MyClusterItem(LatLng(41.201638, 69.231998), "1-nuqta", "Toshkent"),
            MyClusterItem(LatLng(41.205000, 69.235000), "2-nuqta", "Toshkent"),
            MyClusterItem(LatLng(41.208000, 69.238000), "3-nuqta", "Toshkent"),
            MyClusterItem(LatLng(41.210000, 69.240000), "4-nuqta", "Toshkent"),
            MyClusterItem(LatLng(41.212000, 69.242000), "5-nuqta", "Toshkent"),
            MyClusterItem(LatLng(39.654200, 66.959700), "6-nuqta", "Samarqand"),
            MyClusterItem(LatLng(39.774700, 64.428600), "7-nuqta", "Buxoro"),
            MyClusterItem(LatLng(40.386400, 71.786400), "8-nuqta", "Farg'ona"),
            MyClusterItem(LatLng(40.528600, 70.942500), "9-nuqta", "Qo'qon"),
            MyClusterItem(LatLng(41.550300, 60.631700), "10-nuqta", "Urganch")
        )
    }



    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition,
        uiSettings = uiSettings,
        properties = properties
    ){
        Marker(
            state = marker,
           draggable = true,
        )
        Clustering(
            items = clusterItems
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyMapTheme {
//        Greeting("Android")
//    }
//}