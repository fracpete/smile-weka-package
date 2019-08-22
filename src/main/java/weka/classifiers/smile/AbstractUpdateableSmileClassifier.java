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
 * AbstractUpdateableSmileClassifier.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile;

import smile.classification.OnlineClassifier;
import weka.classifiers.UpdateableClassifier;
import weka.core.SmileDatasetUtils;
import weka.core.Instance;

/**
 * Ancestor of incremental SMILE classifiers.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractUpdateableSmileClassifier
  extends AbstractSmileClassifier
  implements UpdateableClassifier {

  private static final long serialVersionUID = -6914517318733509513L;

  /**
   * Updates a classifier using the given instance.
   *
   * @param instance the instance to included
   * @throws Exception if instance could not be incorporated
   * successfully
   */
  @Override
  public void updateClassifier(Instance instance) throws Exception {
    if (m_Model == null)
      throw new IllegalStateException("No model built yet, cannot update!");
    ((OnlineClassifier<double[]>) m_Model).learn(
      SmileDatasetUtils.convertInstance(instance, m_Header.getDataset()),
      (int) SmileDatasetUtils.convertClassValue(instance, m_Header.getDataset()));
  }
}
