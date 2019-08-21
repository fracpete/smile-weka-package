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
 * SmileDatasetWrapper.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.core;

import smile.data.AttributeDataset;

import java.io.Serializable;

/**
 * Wraps AttributeDataset and Instances to get around problem that AttributeDataset
 * is not serialiable.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileDatasetHeader
  implements Serializable {

  private static final long serialVersionUID = 2740443315072249948L;

  /** the SMILE dataset header. */
  protected transient AttributeDataset m_Dataset;

  /** the Weka dataset header. */
  protected Instances m_Instances;

  /**
   * Initializes the header.
   *
   * @param dataset	the dataset
   * @param instances	the instances
   */
  public SmileDatasetHeader(AttributeDataset dataset, Instances instances) {
    m_Dataset   = dataset.head(0);
    m_Instances = new Instances(instances, 0);
  }

  /**
   * Returns the dataset. Recreates it from the Instances structure if necessary.
   *
   * @return  		the dataset
   */
  public AttributeDataset getDataset() {
    if (m_Dataset == null) {
      try {
	m_Dataset = SmileDatasetUtils.convertInstances(m_Instances);
      }
      catch (Exception e) {
        System.err.println(getClass().getName() + ": failed to reconstruct dataset from instances!");
        e.printStackTrace();
      }
    }
    return m_Dataset;
  }

  /**
   * Returns the instances.
   *
   * @return		the instances
   */
  public Instances getInstances() {
    return m_Instances;
  }

  /**
   * Returns the instances as string.
   *
   * @return		the dataset structure
   */
  @Override
  public String toString() {
    return m_Instances.toString();
  }
}
