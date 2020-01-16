/*
 * Copyright 2019, Jeziel Lago - Alex Soares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flownav.processor

import com.google.auto.common.BasicAnnotationProcessor
import com.google.auto.service.AutoService
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType.ISOLATING
import javax.annotation.processing.Processor
import javax.lang.model.SourceVersion

@IncrementalAnnotationProcessor(ISOLATING)
@AutoService(Processor::class)
class FlowNavProcessor : BasicAnnotationProcessor() {

    override fun initSteps(): MutableIterable<ProcessingStep> {
        return mutableListOf(FlowNavProcessingStep(processingEnv))
    }

    override fun getSupportedOptions(): MutableSet<String> {
        return mutableSetOf(
            INCREMENTAL_ISOLATING,
            KAPT_KOTLIN_GENERATED_OPTION_NAME
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()
}
