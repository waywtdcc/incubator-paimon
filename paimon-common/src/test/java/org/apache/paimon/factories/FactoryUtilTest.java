/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/** Tests for {@link FactoryUtil}. */
public class FactoryUtilTest {
    @Test
    public void testDiscoverFactory() {
        DummyFactory factory =
                FactoryUtil.discoverFactory(
                        Thread.currentThread().getContextClassLoader(),
                        DummyFactory.class,
                        DummyFactory.IDENTIFIER);
        assertEquals(DummyFactory.IDENTIFIER, factory.identifier());

        assertThrowsExactly(
                FactoryException.class,
                () ->
                        FactoryUtil.discoverFactory(
                                Thread.currentThread().getContextClassLoader(),
                                DummyFactory.class,
                                "non-exist-factory"),
                String.format(
                        "Could not find any factory for identifier '%s' that implements '%s' in the classpath.",
                        "non-exist-factory", DummyFactory.class.getName()));
    }
}
