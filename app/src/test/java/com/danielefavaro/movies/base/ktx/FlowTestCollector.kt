package com.danielefavaro.movies.base.ktx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class FlowTestCollector<T> constructor(
    scope: CoroutineScope,
    flow: kotlinx.coroutines.flow.Flow<T>
) {
    private var timeoutInMillis: Long = 2000
    private var assert: ((value: T?) -> Unit)? = null
    private val job: Job = scope.launch {
        withTimeout(timeoutInMillis) {
            flow.collect {
                assert?.invoke(it)
            }
        }
    }

    fun assertValue(timeoutInMillis: Long = 2000, block: (value: T?) -> Unit) {
        assertValues(timeoutInMillis = timeoutInMillis) {
            block(it[0])
        }
    }

    fun assertValues(
        numberOfEvents: Int = 1,
        timeoutInMillis: Long = 2000,
        block: (value: List<T?>) -> Unit
    ) {
        this.timeoutInMillis = timeoutInMillis
        val data = mutableListOf<T?>()
        this.assert = {
            data.add(it)
            if (data.size == numberOfEvents) {
                block(data)
                job.cancel()
            }
        }
    }
}

fun <T> Flow<T>.test(scope: CoroutineScope): FlowTestCollector<T> {
    return FlowTestCollector(scope, this)
}