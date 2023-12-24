package com.sadig.exampleapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.sadig.easyoffice.domain.builder.ExcelBuilder
import com.sadig.exampleapp.data.FakeDataSource
import com.sadig.exampleapp.ui.theme.ExampleAppTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = FakeDataSource.getFakeDataAsString())
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = {
                            val map = FakeDataSource.getFakeData()

                            val path = ExcelBuilder(this@MainActivity)
                                .setFileName("report_a.xls")
                                .setData(map)
                                .build()
                            startFileShareIntent(path, this@MainActivity)
                        }) {
                            Text(text = "convert and share")
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExampleAppTheme {
        Greeting("Android")
    }
}


fun startFileShareIntent(filePath: String, context: Context) { // pass the file path where the actual file is located.
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "*/*"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        putExtra(
            Intent.EXTRA_SUBJECT,
            "Sharing file"
        )
        putExtra(
            Intent.EXTRA_TEXT,
            "Sharing file "
        )
        val fileURI = FileProvider.getUriForFile(
            context, context.packageName + ".provider",
            File(filePath)
        )
        putExtra(Intent.EXTRA_STREAM, fileURI)
    }
    context.startActivity(shareIntent)
}