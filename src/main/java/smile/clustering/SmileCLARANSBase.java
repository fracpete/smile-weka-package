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
 * SmileCLARANSBase.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.clustering;

import smile.math.distance.AbstractSmileDistance;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Superclass for SmileCLARANS containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileCLARANSBase
  extends AbstractSmileClusterer {

  /** the flag for {@link #m_Distance}. */
  public final static String DISTANCE = "distance";

  /** the flag for {@link #m_NumClusters}. */
  public final static String NUMCLUSTERS = "num-clusters";

  /** the flag for {@link #m_MaxNeighbor}. */
  public final static String MAXNEIGHBOR = "max-neighbor";

  /** the flag for {@link #m_NumLocalMinima}. */
  public final static String NUMLOCALMINIMA = "num-local-minima";

  /** the distance function to use. */
  protected AbstractSmileDistance m_Distance = getDefaultDistance();

  /** the number of clusters to determine. */
  protected int m_NumClusters = getDefaultNumClusters();

  /** the maximum number of neighbors examined during a search of local minima. */
  protected int m_MaxNeighbor = getDefaultMaxNeighbor();

  /** the number of local minima to search for. */
  protected int m_NumLocalMinima = getDefaultNumLocalMinima();

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
    WekaOptionUtils.addOption(result, distanceTipText(), "" + getDefaultDistance(), DISTANCE);
    WekaOptionUtils.addOption(result, numClustersTipText(), "" + getDefaultNumClusters(), NUMCLUSTERS);
    WekaOptionUtils.addOption(result, maxNeighborTipText(), "" + getDefaultMaxNeighbor(), MAXNEIGHBOR);
    WekaOptionUtils.addOption(result, numLocalMinimaTipText(), "" + getDefaultNumLocalMinima(), NUMLOCALMINIMA);
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
    setDistance((AbstractSmileDistance) WekaOptionUtils.parse(options, DISTANCE, getDefaultDistance()));
    setNumClusters(WekaOptionUtils.parse(options, NUMCLUSTERS, getDefaultNumClusters()));
    setMaxNeighbor(WekaOptionUtils.parse(options, MAXNEIGHBOR, getDefaultMaxNeighbor()));
    setNumLocalMinima(WekaOptionUtils.parse(options, NUMLOCALMINIMA, getDefaultNumLocalMinima()));
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
    WekaOptionUtils.add(result, DISTANCE, getDistance());
    WekaOptionUtils.add(result, NUMCLUSTERS, getNumClusters());
    WekaOptionUtils.add(result, MAXNEIGHBOR, getMaxNeighbor());
    WekaOptionUtils.add(result, NUMLOCALMINIMA, getNumLocalMinima());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default value for distance.
   *
   * @return the default value
   * @see #m_Distance
   */
  protected AbstractSmileDistance getDefaultDistance() {
    return new smile.math.distance.SmileEuclideanDistance();
  }

  /**
   * Returns the distance function to use.
   *
   * @return the current value
   * @see #m_Distance
   */
  public AbstractSmileDistance getDistance() {
    return m_Distance;
  }

  /**
   * Sets the distance function to use.
   *
   * @param value the new value
   * @see #m_Distance
   */
  public void setDistance(AbstractSmileDistance value) {
    m_Distance = value;
  }

  /**
   * Returns the help string for distance.
   *
   * @return the help string
   * @see #m_Distance
   */
  public String distanceTipText() {
    return "The distance function to use.";
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
   * The default value for maxNeighbor.
   *
   * @return the default value
   * @see #m_MaxNeighbor
   */
  protected int getDefaultMaxNeighbor() {
    return -1;
  }

  /**
   * Returns the maximum number of neighbors examined during a search of local minima.
   *
   * @return the current value (value >= 1)
   * @see #m_MaxNeighbor
   */
  public int getMaxNeighbor() {
    return m_MaxNeighbor;
  }

  /**
   * Sets the maximum number of neighbors examined during a search of local minima.
   *
   * @param value the new value (value >= 1)
   * @see #m_MaxNeighbor
   */
  public void setMaxNeighbor(int value) {
    if (value >= 1) {
      m_MaxNeighbor = value;
    }
  }

  /**
   * Returns the help string for maxNeighbor.
   *
   * @return the help string
   * @see #m_MaxNeighbor
   */
  public String maxNeighborTipText() {
    return "The maximum number of neighbors examined during a search of local minima; value >= 1.";
  }

  /**
   * The default value for numLocalMinima.
   *
   * @return the default value
   * @see #m_NumLocalMinima
   */
  protected int getDefaultNumLocalMinima() {
    return 2;
  }

  /**
   * Returns the number of local minima to search for.
   *
   * @return the current value (value >= 1)
   * @see #m_NumLocalMinima
   */
  public int getNumLocalMinima() {
    return m_NumLocalMinima;
  }

  /**
   * Sets the number of local minima to search for.
   *
   * @param value the new value (value >= 1)
   * @see #m_NumLocalMinima
   */
  public void setNumLocalMinima(int value) {
    if (value >= 1) {
      m_NumLocalMinima = value;
    }
  }

  /**
   * Returns the help string for numLocalMinima.
   *
   * @return the help string
   * @see #m_NumLocalMinima
   */
  public String numLocalMinimaTipText() {
    return "The number of local minima to search for; value >= 1.";
  }
}
