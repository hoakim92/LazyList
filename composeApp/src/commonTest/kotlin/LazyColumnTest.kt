import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.test.Test


class LazyColumnTest {
    @Composable
    @Preview
    fun lazyColumnTestApp(content: List<Int>) {
        MaterialTheme {
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .testTag(containerTag).fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(-10.dp)

            ) {
                content.forEach {
                    item { Text(text = "$itemPrefix $it", modifier = Modifier.testTag("$itemPrefix $it")) }
                }
            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun emptyLazyColumn() = runComposeUiTest {
        setContent {
            lazyColumnTestApp(emptyList())
        }
        onNodeWithTag(containerTag).printToLog(containerTag)
//        onNodeWithTag(containerTag).onChildren().assertCountEquals(0)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun lazyColumnBaseScenario() = runComposeUiTest {
        setContent {
            lazyColumnTestApp((1..5).toList())
        }
        onNodeWithTag(containerTag).onChildren().assertCountEquals(5)
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 5")).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun lazyColumnScrollForwardAndReturn() = runComposeUiTest {
        setContent {
            lazyColumnTestApp((1..5).toList())
        }
        onNodeWithTag(containerTag).onChildren().assertCountEquals(5)
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 5")).assertIsDisplayed()
        onNodeWithTag(containerTag).performScrollToNode(hasTestTag("$itemPrefix 1")).assertIsDisplayed()
    }
}