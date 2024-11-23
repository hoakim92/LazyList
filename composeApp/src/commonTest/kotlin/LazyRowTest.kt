import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.test.Test

class LazyRowTest {

    @Composable
    @Preview
    fun lazyRowTestApp(content: List<Int>) {
        MaterialTheme {
            val scrollState = rememberLazyListState()
            LazyRow(
                state = scrollState,
                modifier = Modifier.testTag(containerTag).fillMaxHeight(),
            ) {
                content.forEach {
                    item { Text(text = "$itemPrefix $it", modifier = Modifier.testTag("$itemPrefix $it")) }
                }
            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun emptyLazyRow() = runComposeUiTest {
        setContent {
            lazyRowTestApp(emptyList())
        }
        onNodeWithTag("Items").onChildren().assertCountEquals(0)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun lazyColumnBaseScenario() = runComposeUiTest {
        setContent {
            lazyRowTestApp((1..5).toList())
        }
        onNodeWithTag(containerTag).onChildren().assertCountEquals(5)
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 2")).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun lazyColumnScrollForwardAndReturn() = runComposeUiTest {
        setContent {
            lazyRowTestApp((1..5).toList())
        }
        onNodeWithTag(containerTag).onChildren().assertCountEquals(5)
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 5")).assertIsDisplayed()
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 1")).assertIsDisplayed()
    }
}