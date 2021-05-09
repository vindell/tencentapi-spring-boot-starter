/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tencentcloud.spring.boot.live.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamResult {
	
	private String streamName;
    /**
     * 格式rtmp://domain/AppName/StreamName?txSecret=
     */
    private String rtmpUrl;
    /**
     * 格式http://domain/AppName/StreamName.flv?txSecret=
     */
    private String flvUrl;
    /**
     * 格式http://domain/AppName/StreamName.m3u8
     */
    private String hlsUrl;
    
}
