/*
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

package org.apache.hadoop.mapreduce.split;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.tez.mapreduce.grouper.MapReduceSplitContainer;
import org.apache.tez.mapreduce.grouper.SplitContainer;
import org.apache.tez.mapreduce.grouper.SplitLocationProviderWrapper;

@InterfaceAudience.Private
public class SplitLocationProviderMapReduce implements SplitLocationProviderWrapper {

  private final SplitLocationProvider locationProvider;

  public SplitLocationProviderMapReduce(SplitLocationProvider locationProvider) {
    this.locationProvider = locationProvider;
  }

  @Override
  public String[] getPreferredLocations(SplitContainer rawContainer) throws IOException,
      InterruptedException {
    MapReduceSplitContainer splitContainer = (MapReduceSplitContainer) rawContainer;
    return locationProvider.getLocations(splitContainer.getRawSplit());
  }
}
