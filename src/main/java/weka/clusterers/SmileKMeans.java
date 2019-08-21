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
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Wraps the SMILE KMeans algorithm.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileKMeans
  extends AbstractSmileClusterer {

  private static final long serialVersionUID = -9151643463590262607L;

  public static final String NUM_CLUSTERS = "num-clusters";

  public static final String MAX_ITER = "max-iter";

  public static final String RUNS = "runs";

  /** the number of clusters. */
  protected int m_NumClusters = getDefaultNumClusters();

  /** the maximum number of iterations. */
  protected int m_MaxIter = getDefaultMaxIter();

  /** the number of runs. */
  protected int m_Runs = getDefaultRuns();
  /**
   * Returns a description of the clusterer.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "K-Means clustering. The algorithm partitions n observations into "
      + "k clusters in which each observation belongs to the cluster with the "
      + "nearest mean. Although finding an exact solution to the k-means problem "
      + "for arbitrary input is NP-hard, the standard approach to finding an "
      + "approximate solution (often called Lloyd's algorithm or the k-means "
      + "algorithm) is used widely and frequently finds reasonable solutions quickly.\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/clustering/KMeans.html";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
    WekaOptionUtils.addOption(result, numClustersTipText(), "" + getDefaultNumClusters(), NUM_CLUSTERS);
    WekaOptionUtils.addOption(result, maxIterTipText(), "" + getDefaultMaxIter(), MAX_ITER);
    WekaOptionUtils.addOption(result, runsTipText(), "" + getDefaultRuns(), RUNS);
    WekaOptionUtils.add(result, super.listOptions());
    return WekaOptionUtils.toEnumeration(result);
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    setNumClusters(WekaOptionUtils.parse(options, NUM_CLUSTERS, getDefaultNumClusters()));
    setMaxIter(WekaOptionUtils.parse(options, MAX_ITER, getDefaultMaxIter()));
    setRuns(WekaOptionUtils.parse(options, RUNS, getDefaultRuns()));
    super.setOptions(options);
  }

  /**
   * Gets the current settings.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    List<String> result = new ArrayList<String>();
    WekaOptionUtils.add(result, NUM_CLUSTERS, getNumClusters());
    WekaOptionUtils.add(result, MAX_ITER, getMaxIter());
    WekaOptionUtils.add(result, RUNS, getRuns());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default number of clusters.
   *
   * @return		the default
   */
  protected int getDefaultNumClusters() {
    return 2;
  }

  /**
   * Sets the number of clusters to determine.
   *
   * @param value	the number (>= 2)
   */
  public void setNumClusters(int value) {
    if (value >= 2) {
      m_NumClusters = value;
      reset();
    }
  }

  /**
   * Returns the number of clusters to determine.
   *
   * @return		the number (>= 2)
   */
  public int getNumClusters() {
    return m_NumClusters;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String numClustersTipText() {
    return "The number of clusters to determine (>= 2).";
  }
  
  /**
   * The default maximum number of iterations.
   *
   * @return		the default
   */
  protected int getDefaultMaxIter() {
    return 100;
  }

  /**
   * Sets the maximum number of iterations in each run.
   *
   * @param value	the iterations
   */
  public void setMaxIter(int value) {
    if (value >= 1) {
      m_MaxIter = value;
      reset();
    }
  }

  /**
   * Returns the maximum number of iterations in each run.
   *
   * @return		the iterations
   */
  public int getMaxIter() {
    return m_MaxIter;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String maxIterTipText() {
    return "The maximum number of iterations in each run.";
  }

  /**
   * The default number of runs.
   *
   * @return		the default
   */
  protected int getDefaultRuns() {
    return 1;
  }

  /**
   * Sets the number of runs to perform.
   *
   * @param value	the runs (>= 1)
   */
  public void setRuns(int value) {
    if (value >= 1) {
      m_Runs = value;
      reset();
    }
  }

  /**
   * Returns the number of runs to perform.
   *
   * @return		the runs (>= 1)
   */
  public int getRuns() {
    return m_Runs;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String runsTipText() {
    return "The number of runs (>= 1); if more than 1 then the best model is used.";
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
    if (m_Runs <= 1)
      return new KMeans(data.x(), m_NumClusters, m_MaxIter);
    else
      return new KMeans(data.x(), m_NumClusters, m_MaxIter, m_Runs);
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
