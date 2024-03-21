/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.citrusframework.yaml.actions;

import org.citrusframework.TestCase;
import org.citrusframework.TestCaseMetaInfo;
import org.citrusframework.actions.TransformAction;
import org.citrusframework.util.StringUtils;
import org.citrusframework.yaml.YamlTestLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Christoph Deppisch
 */
public class TransformTest extends AbstractYamlActionTest {

    @Test
    public void shouldLoadTransform() {
        YamlTestLoader testLoader = createTestLoader("classpath:org/citrusframework/yaml/actions/transform-test.yaml");

        testLoader.load();
        TestCase result = testLoader.getTestCase();
        Assert.assertEquals(result.getName(), "TransformTest");
        Assert.assertEquals(result.getMetaInfo().getAuthor(), "Christoph");
        Assert.assertEquals(result.getMetaInfo().getStatus(), TestCaseMetaInfo.Status.FINAL);
        Assert.assertEquals(result.getActionCount(), 2L);
        Assert.assertEquals(result.getTestAction(0).getClass(), TransformAction.class);

        int actionIndex = 0;

        TransformAction action = (TransformAction) result.getTestAction(actionIndex++);
        Assert.assertEquals(action.getTargetVariable(), "result");
        Assert.assertTrue(StringUtils.hasText(action.getXmlData()));
        Assert.assertNull(action.getXmlResourcePath());
        Assert.assertTrue(StringUtils.hasText(action.getXsltData()));
        Assert.assertNull(action.getXsltResourcePath());

        Assert.assertTrue(context.getVariable("result").contains("<p>Message: Hello World!</p>"));

        action = (TransformAction) result.getTestAction(actionIndex);
        Assert.assertEquals(action.getTargetVariable(), "transform-result");
        Assert.assertFalse(StringUtils.hasText(action.getXmlData()));
        Assert.assertNotNull(action.getXmlResourcePath());
        Assert.assertEquals(action.getXmlResourcePath(), "classpath:org/citrusframework/yaml/transform-source.xml");
        Assert.assertFalse(StringUtils.hasText(action.getXsltData()));
        Assert.assertNotNull(action.getXsltResourcePath());
        Assert.assertEquals(action.getXsltResourcePath(), "classpath:org/citrusframework/yaml/transform.xslt");

        Assert.assertTrue(context.getVariable("transform-result").contains("<p>Message: Hello World!</p>"));
    }

}
