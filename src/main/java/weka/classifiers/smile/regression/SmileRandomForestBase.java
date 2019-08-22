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
 * SmileRandomForestBase.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile.regression;

import weka.core.Utils;
import weka.core.WekaOptionUtils;
import weka.classifiers.smile.AbstractSmileRegressor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Superclass for SmileRandomForest containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileRandomForestBase
  extends AbstractSmileRegressor {

  /** the flag for {@link #m_NumTrees}. */
  public final static String NUMTREES = "num-trees";

  /** the flag for {@link #m_NumFeatures}. */
  public final static String NUMFEATURES = "num-features";

  /** the flag for {@link #m_MaxNodes}. */
  public final static String MAXNODES = "max-nodes";

  /** the flag for {@link #m_MinNodeSize}. */
  public final static String MINNODESIZE = "min-node-size";

  /** the flag for {@link #m_SubSample}. */
  public final static String SUBSAMPLE = "sub-sample";

  /** the number of trees to use. */
  protected int m_NumTrees = getDefaultNumTrees();

  /** the number of features to use; use -1 for sqrt(#attributes). */
  protected int m_NumFeatures = getDefaultNumFeatures();

  /** the maximum number of leaf nodes. */
  protected int m_MaxNodes = getDefaultMaxNodes();

  /** the minimum node size. */
  protected int m_MinNodeSize = getDefaultMinNodeSize();

  /** the sub-sample size to use. */
  protected double m_SubSample = getDefaultSubSample();

  /**
   * Returns a desription of the class.
   *
   * @return the description
   */
  public abstract String globalInfo();

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
    WekaOptionUtils.addOption(result, numTreesTipText(), "" + getDefaultNumTrees(), NUMTREES);
    WekaOptionUtils.addOption(result, numFeaturesTipText(), "" + getDefaultNumFeatures(), NUMFEATURES);
    WekaOptionUtils.addOption(result, maxNodesTipText(), "" + getDefaultMaxNodes(), MAXNODES);
    WekaOptionUtils.addOption(result, minNodeSizeTipText(), "" + getDefaultMinNodeSize(), MINNODESIZE);
    WekaOptionUtils.addOption(result, subSampleTipText(), "" + getDefaultSubSample(), SUBSAMPLE);
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
    setNumTrees(WekaOptionUtils.parse(options, NUMTREES, getDefaultNumTrees()));
    setNumFeatures(WekaOptionUtils.parse(options, NUMFEATURES, getDefaultNumFeatures()));
    setMaxNodes(WekaOptionUtils.parse(options, MAXNODES, getDefaultMaxNodes()));
    setMinNodeSize(WekaOptionUtils.parse(options, MINNODESIZE, getDefaultMinNodeSize()));
    setSubSample(WekaOptionUtils.parse(options, SUBSAMPLE, getDefaultSubSample()));
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
    WekaOptionUtils.add(result, NUMTREES, getNumTrees());
    WekaOptionUtils.add(result, NUMFEATURES, getNumFeatures());
    WekaOptionUtils.add(result, MAXNODES, getMaxNodes());
    WekaOptionUtils.add(result, MINNODESIZE, getMinNodeSize());
    WekaOptionUtils.add(result, SUBSAMPLE, getSubSample());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default value for numTrees.
   *
   * @return the default value
   * @see #m_NumTrees
   */
  protected int getDefaultNumTrees() {
    return 100;
  }

  /**
   * Returns the number of trees to use.
   *
   * @return the current value (value >= 1)
   * @see #m_NumTrees
   */
  public int getNumTrees() {
    return m_NumTrees;
  }

  /**
   * Sets the number of trees to use.
   *
   * @param value the new value (value >= 1)
   * @see #m_NumTrees
   */
  public void setNumTrees(int value) {
    if (value >= 1) {
      m_NumTrees = value;
    }
  }

  /**
   * Returns the help string for numTrees.
   *
   * @return the help string
   * @see #m_NumTrees
   */
  public String numTreesTipText() {
    return "The number of trees to use; value >= 1.";
  }

  /**
   * The default value for numFeatures.
   *
   * @return the default value
   * @see #m_NumFeatures
   */
  protected int getDefaultNumFeatures() {
    return -1;
  }

  /**
   * Returns the number of features to use; use -1 for sqrt(#attributes).
   *
   * @return the current value ((value >= 1) || (value == -1))
   * @see #m_NumFeatures
   */
  public int getNumFeatures() {
    return m_NumFeatures;
  }

  /**
   * Sets the number of features to use; use -1 for sqrt(#attributes).
   *
   * @param value the new value ((value >= 1) || (value == -1))
   * @see #m_NumFeatures
   */
  public void setNumFeatures(int value) {
    if ((value >= 1) || (value == -1)) {
      m_NumFeatures = value;
    }
  }

  /**
   * Returns the help string for numFeatures.
   *
   * @return the help string
   * @see #m_NumFeatures
   */
  public String numFeaturesTipText() {
    return "The number of features to use; use -1 for sqrt(#attributes); (value >= 1) || (value == -1).";
  }

  /**
   * The default value for maxNodes.
   *
   * @return the default value
   * @see #m_MaxNodes
   */
  protected int getDefaultMaxNodes() {
    return 100;
  }

  /**
   * Returns the maximum number of leaf nodes.
   *
   * @return the current value (value >= 2)
   * @see #m_MaxNodes
   */
  public int getMaxNodes() {
    return m_MaxNodes;
  }

  /**
   * Sets the maximum number of leaf nodes.
   *
   * @param value the new value (value >= 2)
   * @see #m_MaxNodes
   */
  public void setMaxNodes(int value) {
    if (value >= 2) {
      m_MaxNodes = value;
    }
  }

  /**
   * Returns the help string for maxNodes.
   *
   * @return the help string
   * @see #m_MaxNodes
   */
  public String maxNodesTipText() {
    return "The maximum number of leaf nodes; value >= 2.";
  }

  /**
   * The default value for minNodeSize.
   *
   * @return the default value
   * @see #m_MinNodeSize
   */
  protected int getDefaultMinNodeSize() {
    return 5;
  }

  /**
   * Returns the minimum node size.
   *
   * @return the current value (value >= 1)
   * @see #m_MinNodeSize
   */
  public int getMinNodeSize() {
    return m_MinNodeSize;
  }

  /**
   * Sets the minimum node size.
   *
   * @param value the new value (value >= 1)
   * @see #m_MinNodeSize
   */
  public void setMinNodeSize(int value) {
    if (value >= 1) {
      m_MinNodeSize = value;
    }
  }

  /**
   * Returns the help string for minNodeSize.
   *
   * @return the help string
   * @see #m_MinNodeSize
   */
  public String minNodeSizeTipText() {
    return "The minimum node size; value >= 1.";
  }

  /**
   * The default value for subSample.
   *
   * @return the default value
   * @see #m_SubSample
   */
  protected double getDefaultSubSample() {
    return 1.0;
  }

  /**
   * Returns the sub-sample size to use.
   *
   * @return the current value ((value > 0) && (value <= 1))
   * @see #m_SubSample
   */
  public double getSubSample() {
    return m_SubSample;
  }

  /**
   * Sets the sub-sample size to use.
   *
   * @param value the new value ((value > 0) && (value <= 1))
   * @see #m_SubSample
   */
  public void setSubSample(double value) {
    if ((value > 0) && (value <= 1)) {
      m_SubSample = value;
    }
  }

  /**
   * Returns the help string for subSample.
   *
   * @return the help string
   * @see #m_SubSample
   */
  public String subSampleTipText() {
    return "The sub-sample size to use; (value > 0) && (value <= 1).";
  }
}
