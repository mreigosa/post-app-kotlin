package instrumentation.presentation

import com.mreigar.presentation.model.PostViewModel

object PresentationEntityInstrument {

    fun givenPostViewModel() = PostViewModel(1, 1, "title", "body")
}