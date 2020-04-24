package instrumentation.data

import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.EmailEmoji
import com.mreigar.domain.repository.ComplementaryDetailsRepositoryContract
import instrumentation.domain.DomainEntityInstrument.givenEmailEmoji

object ComplementaryDetailsRepositoryInstrument {

    fun givenComplementaryDetailsRepository(
        status: RepositoryStatus = RepositoryStatus.SUCCESS,
        emailEmojis: List<EmailEmoji>? = null
    ) = object : ComplementaryDetailsRepositoryContract {

        override fun getEmailEmojis(): Result<List<EmailEmoji>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(emailEmojis ?: listOf(givenEmailEmoji()), DataStatus.LOCAL)
                RepositoryStatus.ERROR -> NoData
            }
    }
}