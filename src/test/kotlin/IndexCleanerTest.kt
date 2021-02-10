import org.junit.jupiter.api.Test
import random.IndexCleaner

internal class IndexCleanerTest {

    @Test
    fun cleanup() {
        IndexCleaner().cleanup(1567296000000)
    }
}