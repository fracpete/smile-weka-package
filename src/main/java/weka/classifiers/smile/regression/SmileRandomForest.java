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
 * SmileRandomForest.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile.regression;

import smile.data.AttributeDataset;
import smile.math.Math;
import smile.regression.RandomForest;
import smile.regression.Regression;
import weka.classifiers.AbstractSmileRegressor;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * SMILE RandomForest (regression).
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileRandomForest
  extends AbstractSmileRegressor {

  private static final long serialVersionUID = -6558986110434792292L;

  public static final String NUM_TREES = "num-trees";

  public static final String NUM_FEATURES = "num-features";

  public static final String MAX_NODES = "max-nodes";

  public static final String MIN_NODE_SIZE = "min-node-size";

  public static final String SUB_SAMPLE = "sub-sample";

  /** the number of trees. */
  protected int m_NumTrees = getDefaultNumTrees();

  /** the number of features to split on. */
  protected int m_NumFeatures = getDefaultNumFeatures();

  /** the maximum number of leaf nodes. */
  protected int m_MaxNodes = getDefaultMaxNodes();

  /** the minimum node size. */
  protected int m_MinNodeSize = getDefaultMinNodeSize();

  /** the sub-sample size (0-1). */
  protected double m_SubSample = getDefaultSubSample();

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Random forest for regression. Random forest is an ensemble method "
      + "that consists of many regression trees and outputs the average of "
      + "individual trees. The method combines bagging idea and the random "
      + "selection of features. \n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/regression/RandomForest.html";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
    WekaOptionUtils.addOption(result, numTreesTipText(), "" + getDefaultNumTrees(), NUM_TREES);
    WekaOptionUtils.addOption(result, numFeaturesTipText(), "" + getDefaultNumFeatures(), NUM_FEATURES);
    WekaOptionUtils.addOption(result, maxNodesTipText(), "" + getDefaultMaxNodes(), MAX_NODES);
    WekaOptionUtils.addOption(result, minNodeSizeTipText(), "" + getDefaultMinNodeSize(), MIN_NODE_SIZE);
    WekaOptionUtils.addOption(result, subSampleTipText(), "" + getDefaultSubSample(), SUB_SAMPLE);
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
    setNumTrees(WekaOptionUtils.parse(options, NUM_TREES, getDefaultNumTrees()));
    setNumFeatures(WekaOptionUtils.parse(options, NUM_FEATURES, getDefaultNumFeatures()));
    setMaxNodes(WekaOptionUtils.parse(options, MAX_NODES, getDefaultMaxNodes()));
    setMinNodeSize(WekaOptionUtils.parse(options, MIN_NODE_SIZE, getDefaultMinNodeSize()));
    setSubSample(WekaOptionUtils.parse(options, SUB_SAMPLE, getDefaultSubSample()));
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
    WekaOptionUtils.add(result, NUM_TREES, getNumTrees());
    WekaOptionUtils.add(result, NUM_FEATURES, getNumFeatures());
    WekaOptionUtils.add(result, MAX_NODES, getMaxNodes());
    WekaOptionUtils.add(result, MIN_NODE_SIZE, getMinNodeSize());
    WekaOptionUtils.add(result, SUB_SAMPLE, getSubSample());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default number of trees.
   *
   * @return		the default
   */
  protected int getDefaultNumTrees() {
    return 100;
  }

  /**
   * Sets the number of trees to use.
   *
   * @param value	the trees (>= 1)
   */
  public void setNumTrees(int value) {
    if (value >= 1) {
      m_NumTrees = value;
      reset();
    }
  }

  /**
   * Returns the number of trees to use.
   *
   * @return		the trees (>= 1)
   */
  public int getNumTrees() {
    return m_NumTrees;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String numTreesTipText() {
    return "The number of trees to use (>= 1).";
  }

  /**
   * The default number of features.
   *
   * @return		the default
   */
  protected int getDefaultNumFeatures() {
    return -1;
  }

  /**
   * Sets the number of features to use.
   *
   * @param value	the features (>= 1 or -1 for sqrt(#attributes))
   */
  public void setNumFeatures(int value) {
    if ((value >= 1) || (value == -1)) {
      m_NumFeatures = value;
      reset();
    }
  }

  /**
   * Returns the number of features to use.
   *
   * @return		the features (>= 1 or -1 for sqrt(#attributes))
   */
  public int getNumFeatures() {
    return m_NumFeatures;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String numFeaturesTipText() {
    return "The number of features to use (>= 1 or -1 for sqrt(#attributes)).";
  }

  /**
   * The default maximum number of leaf nodes.
   *
   * @return		the default
   */
  protected int getDefaultMaxNodes() {
    return 100;
  }

  /**
   * Sets the maximum number of leaf nodes.
   *
   * @param value	the maximum (>= 2)
   */
  public void setMaxNodes(int value) {
    if (value >= 2) {
      m_MaxNodes = value;
      reset();
    }
  }

  /**
   * Returns the maximum number of leaf nodes.
   *
   * @return		the maximum (>= 2)
   */
  public int getMaxNodes() {
    return m_MaxNodes;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String maxNodesTipText() {
    return "The maximum number of leaf nodes (>= 2).";
  }

  /**
   * The default minimum size of nodes.
   *
   * @return		the default
   */
  protected int getDefaultMinNodeSize() {
    return 5;
  }

  /**
   * Sets the minimum size for nodes.
   *
   * @param value	the size (>= 1)
   */
  public void setMinNodeSize(int value) {
    if (value >= 1) {
      m_MinNodeSize = value;
      reset();
    }
  }

  /**
   * Returns the minimum size for nodes.
   *
   * @return		the size (>= 1)
   */
  public int getMinNodeSize() {
    return m_MinNodeSize;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String minNodeSizeTipText() {
    return "The minimum node size (>= 1).";
  }

  /**
   * The default sub sample size.
   *
   * @return		the default
   */
  protected double getDefaultSubSample() {
    return 1.0;
  }

  /**
   * Sets the sub sample size.
   *
   * @param value	the sample size (0 < x <= 1)
   */
  public void setSubSample(double value) {
    if ((value > 0) && (value <= 1)) {
      m_SubSample = value;
      reset();
    }
  }

  /**
   * Returns the sub sample size.
   *
   * @return		the sample size (0 < x <= 1)
   */
  public double getSubSample() {
    return m_SubSample;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String subSampleTipText() {
    return "The sub-sample size to use (0 < x <= 1).";
  }

  /**
   * Returns default capabilities of the classifier.
   *
   * @return the capabilities of this classifier
   */
  @Override
  public Capabilities getCapabilities() {
    Capabilities result = super.getCapabilities();
    result.disableAll();

    // attributes
    result.enable(Capability.NOMINAL_ATTRIBUTES);
    result.enable(Capability.NUMERIC_ATTRIBUTES);
    result.enable(Capability.DATE_ATTRIBUTES);
    result.enable(Capability.MISSING_VALUES);

    // class
    result.enable(Capability.NUMERIC_CLASS);
    result.enable(Capability.MISSING_CLASS_VALUES);

    return result;
  }

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  protected Regression<double[]> buildClassifier(AttributeDataset data) throws Exception {
    return new RandomForest(
      data.attributes(),
      data.x(),
      data.y(),
      m_NumTrees,
      m_MaxNodes,
      m_MinNodeSize,
      m_NumFeatures == -1 ? (int) Math.floor(Math.sqrt(data.attributes().length)) : m_NumFeatures,
      m_SubSample,
      null);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileRandomForest(), args);
  }
}
