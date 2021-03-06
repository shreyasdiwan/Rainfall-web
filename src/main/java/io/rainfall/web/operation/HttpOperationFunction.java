/*
 * Copyright 2014 Aurélien Broszniowski
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

package io.rainfall.web.operation;

import io.rainfall.statistics.FunctionExecutor;
import io.rainfall.statistics.OperationFunction;
import io.rainfall.web.statistics.HttpResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * @author Aurelien Broszniowski
 */
public class HttpOperationFunction extends OperationFunction<HttpResult> {

  private HttpClient client;
  private HttpRequestBase httpRequestBase;

  public FunctionExecutor execute(HttpClient client, final HttpRequestBase httpRequestBase) {
    this.client = client;
    this.httpRequestBase = httpRequestBase;
    return functionExecutor;
  }

  @Override
  public HttpResult apply() throws Exception {
    HttpResponse response = client.execute(httpRequestBase);

    if (response.getStatusLine().getStatusCode() == 200)
      return HttpResult.OK;
    else
      return HttpResult.KO;
  }


}
