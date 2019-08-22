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
 * SmileCLARANS.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.clustering;

import smile.data.AttributeDataset;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 * Wraps the SMILE CLARANS algorithm.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileCLARANS
  extends SmileCLARANSBase {

  private static final long serialVersionUID = -9151643463590262607L;
  /**
   * Returns a description of the clusterer.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Clustering Large Applications based upon RANdomized Search. "
      + "CLARANS is an efficient medoid-based clustering algorithm. "
      + "The k-medoids algorithm is an adaptation of the k-means algorithm. "
      + "Rather than calculate the mean of the items in each cluster, a "
      + "representative item, or medoid, is chosen for each cluster at each "
      + "iteration. In CLARANS, the process of finding k medoids from n objects "
      + "is viewed abstractly as searching through a certain graph. In the graph, "
      + "a node is represented by a set of k objects as selected medoids. "
      + "Two nodes are neighbors if their sets differ by only one object. "
      + "In each iteration, CLARANS considers a set of randomly chosen neighbor "
      + "nodes as candidate of new medoids. We will move to the neighbor node if "
      + "the neighbor is a better choice for medoids. Otherwise, a local optima "
      + "is discovered. The entire process is repeated multiple time to find better.\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/clustering/CLARANS.html";
  }

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
    return new CLARANS<double[]>(
      data.x(),
      m_Distance.getDistance(),
      m_NumClusters,
      (m_MaxNeighbor < 1 ? (int) (0.02 * m_NumClusters * (data.size() - m_NumClusters)) : m_MaxNeighbor),
      m_NumLocalMinima);
  }

  /**
   * Returns the number of clusters.
   *
   * @return the number of clusters generated for a training dataset.
   * @throws Exception if number of clusters could not be returned
   *              successfully
   */
  @Override
  public int numberOfClusters() throws Exception {
    return m_NumClusters;
  }

  /**
   * Executes the clusterer from the commandline.
   *
   * @param args	the parameters to use
   * @throws Exception	if execution fails
   */
  public static void main(String[] args) throws Exception {
    runClusterer(new SmileCLARANS(), args);
  }
}
