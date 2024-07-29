package example.com.client

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class MockStudent @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("email") val email: String
)
