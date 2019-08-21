/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * SmileKMeans.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.clusterers;

import smile.clustering.Clustering;
import smile.clustering.KMeans;
import smile.data.AttributeDataset;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 * Wraps the SMILE KMeans algorithm.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileKMeans
  extends AbstractSmileClusterer {

  private static final long serialVersionUID = -9151643463590262607L;

  /**
   * Returns default capabilities of the clusterer.
   *
   * @return the capabilities of this clusterer
   */
  @Override
  public Capabilities getCapabilities() {
    Capabilities result = super.getCapabilities();
    result.disableAll();
    result.enable(Capability.NO_CLASS);

    // attributes
    result.enable(Capability.NOMINAL_ATTRIBUTES);
    result.enable(Capability.NUMERIC_ATTRIBUTES);

    return result;
  }

  /**
   * Builds the clusterer.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  protected Clustering<double[]> buildClusterer(AttributeDataset data) throws Exception {
    return new KMeans(data.x(), 3);
  }

  /**
   * Executes the clusterer from the commandline.
   *
   * @param args	the parameters to use
   * @throws Exception	if execution fails
   */
  public static void main(String[] args) throws Exception {
    runClusterer(new SmileKMeans(), args);
  }
}
