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
 * SmileKMeansBase.java
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
 * Superclass for SmileKMeans containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileKMeansBase
  extends AbstractSmileClusterer {

  /** the flag for {@link #m_NumClusters}. */
  public final static String NUMCLUSTERS = "num-clusters";

  /** the flag for {@link #m_MaxIter}. */
  public final static String MAXITER = "max-iter";

  /** the flag for {@link #m_Runs}. */
  public final static String RUNS = "runs";

  /** the number of clusters to determine. */
  protected int m_NumClusters = getDefaultNumClusters();

  /** the maximum number of iterations in each run. */
  protected int m_MaxIter = getDefaultMaxIter();

  /** the number of runs; if more than 1 then the best model is used. */
  protected int m_Runs = getDefaultRuns();

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
    WekaOptionUtils.addOption(result, maxIterTipText(), "" + getDefaultMaxIter(), MAXITER);
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
    setNumClusters(WekaOptionUtils.parse(options, NUMCLUSTERS, getDefaultNumClusters()));
    setMaxIter(WekaOptionUtils.parse(options, MAXITER, getDefaultMaxIter()));
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
    WekaOptionUtils.add(result, NUMCLUSTERS, getNumClusters());
    WekaOptionUtils.add(result, MAXITER, getMaxIter());
    WekaOptionUtils.add(result, RUNS, getRuns());
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
   * The default value for maxIter.
   *
   * @return the default value
   * @see #m_MaxIter
   */
  protected int getDefaultMaxIter() {
    return 100;
  }

  /**
   * Returns the maximum number of iterations in each run.
   *
   * @return the current value (value >= 1)
   * @see #m_MaxIter
   */
  public int getMaxIter() {
    return m_MaxIter;
  }

  /**
   * Sets the maximum number of iterations in each run.
   *
   * @param value the new value (value >= 1)
   * @see #m_MaxIter
   */
  public void setMaxIter(int value) {
    if (value >= 1) {
      m_MaxIter = value;
    }
  }

  /**
   * Returns the help string for maxIter.
   *
   * @return the help string
   * @see #m_MaxIter
   */
  public String maxIterTipText() {
    return "The maximum number of iterations in each run; value >= 1.";
  }

  /**
   * The default value for runs.
   *
   * @return the default value
   * @see #m_Runs
   */
  protected int getDefaultRuns() {
    return 1;
  }

  /**
   * Returns the number of runs; if more than 1 then the best model is used.
   *
   * @return the current value (value >= 1)
   * @see #m_Runs
   */
  public int getRuns() {
    return m_Runs;
  }

  /**
   * Sets the number of runs; if more than 1 then the best model is used.
   *
   * @param value the new value (value >= 1)
   * @see #m_Runs
   */
  public void setRuns(int value) {
    if (value >= 1) {
      m_Runs = value;
    }
  }

  /**
   * Returns the help string for runs.
   *
   * @return the help string
   * @see #m_Runs
   */
  public String runsTipText() {
    return "The number of runs; if more than 1 then the best model is used; value >= 1.";
  }
}
