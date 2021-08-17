/*
 * Copyright (c) 2021, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.ballerina.openapi.extension;

import io.ballerina.openapi.extension.doc.ResourcePackagingService;
import io.ballerina.projects.plugins.CompilerLifecycleEventContext;
import io.ballerina.projects.plugins.CompilerLifecycleTask;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * {@code HttpDocGeneratedTask} handles post compile tasks related to OpenAPI doc generation.
 */
public class OpenApiDocPackagingTask implements CompilerLifecycleTask<CompilerLifecycleEventContext> {
    private static final PrintStream ERR = System.err;

    private final ResourcePackagingService packagingService;

    public OpenApiDocPackagingTask() {
        this.packagingService = new ResourcePackagingService();
    }

    @Override
    public void perform(CompilerLifecycleEventContext context) {
        Optional<Path> executablePath = context.getGeneratedArtifactPath();
        executablePath.ifPresent(this::updateResources);
    }

    private void updateResources(Path executablePath) {
        Path executableJarAbsPath = executablePath.toAbsolutePath();
        // get the path for `target/bin`
        Path targetBinPath = executableJarAbsPath.getParent();
        if (null != targetBinPath && Files.exists(targetBinPath)) {
            String executableJarFileName = executableJarAbsPath.toFile().getName();
            try {
                this.packagingService.updateExecutableJar(targetBinPath, executableJarFileName);
            } catch (IOException e) {
                ERR.println("error [open-api extension]: " + e.getLocalizedMessage());
            }
        }
    }
}
