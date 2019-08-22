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
 * SmileBIRCHBase.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.clusterers.smile;

import weka.core.Utils;
import weka.core.WekaOptionUtils;
import weka.clusterers.smile.AbstractSmileClusterer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Superclass for SmileBIRCH containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileBIRCHBase
  extends AbstractSmileClusterer {

  /** the flag for {@link #m_NumClusters}. */
  public final static String NUMCLUSTERS = "num-clusters";

  /** the flag for {@link #m_Dimensionality}. */
  public final static String DIMENSIONALITY = "dimensionality";

  /** the flag for {@link #m_Branching}. */
  public final static String BRANCHING = "branching";

  /** the flag for {@link #m_MaxRadius}. */
  public final static String MAXRADIUS = "max-radius";

  /** the number of clusters to determine. */
  protected int m_NumClusters = getDefaultNumClusters();

  /** the dimensionality of the data. */
  protected int m_Dimensionality = getDefaultDimensionality();

  /** the branching factor, maximum number of children nodes.. */
  protected int m_Branching = getDefaultBranching();

  /** the maximum radius of a sub-cluster. */
  protected double m_MaxRadius = getDefaultMaxRadius();

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
    WekaOptionUtils.addOption(result, numClustersTipText(), "" + getDefaultNumClusters(), NUMCLUSTERS);
    WekaOptionUtils.addOption(result, dimensionalityTipText(), "" + getDefaultDimensionality(), DIMENSIONALITY);
    WekaOptionUtils.addOption(result, branchingTipText(), "" + getDefaultBranching(), BRANCHING);
    WekaOptionUtils.addOption(result, maxRadiusTipText(), "" + getDefaultMaxRadius(), MAXRADIUS);
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
    setNumClusters(WekaOptionUtils.parse(options, NUMCLUSTERS, getDefaultNumClusters()));
    setDimensionality(WekaOptionUtils.parse(options, DIMENSIONALITY, getDefaultDimensionality()));
    setBranching(WekaOptionUtils.parse(options, BRANCHING, getDefaultBranching()));
    setMaxRadius(WekaOptionUtils.parse(options, MAXRADIUS, getDefaultMaxRadius()));
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
    WekaOptionUtils.add(result, NUMCLUSTERS, getNumClusters());
    WekaOptionUtils.add(result, DIMENSIONALITY, getDimensionality());
    WekaOptionUtils.add(result, BRANCHING, getBranching());
    WekaOptionUtils.add(result, MAXRADIUS, getMaxRadius());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default value for numClusters.
   *
   * @return the default value
   * @see #m_NumClusters
   */
  protected int getDefaultNumClusters() {
    return 2;
  }

  /**
   * Returns the number of clusters to determine.
   *
   * @return the current value (value >= 2)
   * @see #m_NumClusters
   */
  public int getNumClusters() {
    return m_NumClusters;
  }

  /**
   * Sets the number of clusters to determine.
   *
   * @param value the new value (value >= 2)
   * @see #m_NumClusters
   */
  public void setNumClusters(int value) {
    if (value >= 2) {
      m_NumClusters = value;
    }
  }

  /**
   * Returns the help string for numClusters.
   *
   * @return the help string
   * @see #m_NumClusters
   */
  public String numClustersTipText() {
    return "The number of clusters to determine; value >= 2.";
  }

  /**
   * The default value for dimensionality.
   *
   * @return the default value
   * @see #m_Dimensionality
   */
  protected int getDefaultDimensionality() {
    return 2;
  }

  /**
   * Returns the dimensionality of the data.
   *
   * @return the current value (value >= 1)
   * @see #m_Dimensionality
   */
  public int getDimensionality() {
    return m_Dimensionality;
  }

  /**
   * Sets the dimensionality of the data.
   *
   * @param value the new value (value >= 1)
   * @see #m_Dimensionality
   */
  public void setDimensionality(int value) {
    if (value >= 1) {
      m_Dimensionality = value;
    }
  }

  /**
   * Returns the help string for dimensionality.
   *
   * @return the help string
   * @see #m_Dimensionality
   */
  public String dimensionalityTipText() {
    return "The dimensionality of the data; value >= 1.";
  }

  /**
   * The default value for branching.
   *
   * @return the default value
   * @see #m_Branching
   */
  protected int getDefaultBranching() {
    return 2;
  }

  /**
   * Returns the branching factor, maximum number of children nodes..
   *
   * @return the current value (value >= 1)
   * @see #m_Branching
   */
  public int getBranching() {
    return m_Branching;
  }

  /**
   * Sets the branching factor, maximum number of children nodes..
   *
   * @param value the new value (value >= 1)
   * @see #m_Branching
   */
  public void setBranching(int value) {
    if (value >= 1) {
      m_Branching = value;
    }
  }

  /**
   * Returns the help string for branching.
   *
   * @return the help string
   * @see #m_Branching
   */
  public String branchingTipText() {
    return "The branching factor, maximum number of children nodes.; value >= 1.";
  }

  /**
   * The default value for maxRadius.
   *
   * @return the default value
   * @see #m_MaxRadius
   */
  protected double getDefaultMaxRadius() {
    return 1.0;
  }

  /**
   * Returns the maximum radius of a sub-cluster.
   *
   * @return the current value (value > 0.0)
   * @see #m_MaxRadius
   */
  public double getMaxRadius() {
    return m_MaxRadius;
  }

  /**
   * Sets the maximum radius of a sub-cluster.
   *
   * @param value the new value (value > 0.0)
   * @see #m_MaxRadius
   */
  public void setMaxRadius(double value) {
    if (value > 0.0) {
      m_MaxRadius = value;
    }
  }

  /**
   * Returns the help string for maxRadius.
   *
   * @return the help string
   * @see #m_MaxRadius
   */
  public String maxRadiusTipText() {
    return "The maximum radius of a sub-cluster; value > 0.0.";
  }
}
