package sctestingplayground

interface InstagramErrorContent {
    val photoVideoUrl: String?
    val tags: List<String>?
    val errorCode: ErrorCode?
}

@InstagramErrorCode
enum class ErrorCode {
    Declined,
    OverLimit,
    TimeOut
}

sealed class InstagramErrorType : InstagramErrorContent {

    data class DeclinePost(
            override val photoVideoUrl: String,
            override val tags: List<String>,
            override val errorCode: ErrorCode,
            val textContent: String,
            val location: String
    ) : InstagramErrorType()

    data class TooLongVideoPost(
            override val photoVideoUrl: String,
            override val tags: List<String>,
            override val errorCode: ErrorCode,
            val textContent: String,
            val location: String
    ) : InstagramErrorType()

    data class TimeoutStory(
            override val photoVideoUrl: String,
            override val tags: List<String>,
            override val errorCode: ErrorCode,
            val texts: List<String>
    ) : InstagramErrorType()

    data class TooShortIGTvPost(
            override val photoVideoUrl: String,
            override val tags: List<String>,
            override val errorCode: ErrorCode,
            val textContent: String
    ) : InstagramErrorType()

    data class TimeOutIGTvPost(
            override val photoVideoUrl: String,
            override val tags: List<String>,
            override val errorCode: ErrorCode,
            val textContent: String
    ) : InstagramErrorType()
}