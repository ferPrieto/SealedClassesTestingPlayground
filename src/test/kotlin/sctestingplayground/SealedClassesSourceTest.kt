package sctestingplayground

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass

class InstagramErrorTest {
    companion object {
        @Suppress("unused")
        @JvmStatic
        private fun checkSealedClassesByType() = Stream.of(
            Arguments.of(
                InstagramErrorType.DeclinePost(
                    photoVideoUrl = "https://www.instagram.com/p/B_2B2nwpS5g/",
                    tags = listOf("pullup", "bodyweight"),
                    errorCode = ErrorCode.Declined,
                    textContent = "Content",
                    location = "Venice Beach"
                ), InstagramErrorType.DeclinePost::class
            ),
            Arguments.of(
                InstagramErrorType.TooLongVideoPost(
                    photoVideoUrl = "https://www.instagram.com/tv/B9j2CshJr9u/",
                    tags = listOf("tutorial", "handstand"),
                    errorCode = ErrorCode.OverLimit,
                    textContent = "Content",
                    location = "London, United Kingdom"
                ), InstagramErrorType.TooLongVideoPost::class
            ),
            Arguments.of(
                InstagramErrorType.TimeoutStory(
                    photoVideoUrl = "https://www.instagram.com/p/B3aHttphr9V/",
                    tags = listOf("pullup", "bodyweight"),
                    errorCode = ErrorCode.TimeOut,
                    texts = listOf("Title", "Subtitle")
                ), InstagramErrorType.TimeoutStory::class
            ),
            Arguments.of(
                InstagramErrorType.TooShortIGTvPost(
                    photoVideoUrl = "https://www.instagram.com/tv/B9MZ3iDptJi/",
                    tags = listOf("core", "bodyWeight"),
                    errorCode = ErrorCode.OverLimit,
                    textContent = "CORE CHALLENGE"
                ),
                InstagramErrorType.TooShortIGTvPost::class
            ),
            Arguments.of(
                InstagramErrorType.TimeOutIGTvPost(
                    photoVideoUrl = "https://www.instagram.com/tv/B_hccoAjjiQ/",
                    tags = listOf("core", "bodyweight"),
                    errorCode = ErrorCode.TimeOut,
                    textContent = "Flexibility Helpers"
                ),
                InstagramErrorType.TimeOutIGTvPost::class
            )
        )
    }

    @ParameterizedTest
    @MethodSource("checkSealedClassesByType")
    fun <I : InstagramErrorType> `can check sealed classes by type`(
            instagramError: InstagramErrorType,
            expectedSealedClass: KClass<I>
    ) {
        Assertions.assertEquals(instagramError::class.simpleName!!, expectedSealedClass.simpleName)
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class MixedConstructors {

        private val instagramErrors = mutableListOf<String>()

        @AfterAll
        fun check() {
            Assertions.assertEquals(
                listOf(
                    "DeclinePost",
                    "TooLongVideoPost",
                    "TimeoutStory",
                    "TooShortIGTvPost",
                    "TimeOutIGTvPost"
                ), instagramErrors
            )
        }

        @ParameterizedTest
        @SealedClassesSource(factoryClass = InstagramTypeFactory::class)
        fun build(member: InstagramErrorType) {
            instagramErrors.add(member::class.simpleName!!)
        }
    }
}