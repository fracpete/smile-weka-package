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
 * AbstractSmileRegressor.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.regression;

import smile.data.AttributeDataset;
import weka.classifiers.AbstractClassifier;
import smile.core.SmileDatasetHeader;
import smile.core.SmileDatasetUtils;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

/**
 * Ancestor for SMILE regression algorithms.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractSmileRegressor
  extends AbstractClassifier {

  private static final long serialVersionUID = 8061087017316008521L;

  /** the dataset structure. */
  protected SmileDatasetHeader m_Header;

  /** the model. */
  protected smile.regression.Regression<double[]> m_Model;

  /**
   * Returns a description of the regressor.
   *
   * @return the description
   */
  public abstract String globalInfo();

  /**
   * Resets the scheme.
   */
  protected void reset() {
    m_Header = null;
    m_Model  = null;
  }

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  protected abstract smile.regression.Regression<double[]> buildClassifier(AttributeDataset data) throws Exception;

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  public void buildClassifier(Instances data) throws Exception {
    AttributeDataset	dataset;

    reset();
    getCapabilities().testWithFail(data);
    data     = new Instances(data);
    data.deleteWithMissingClass();
    dataset  = SmileDatasetUtils.convertInstances(data);
    m_Header = new SmileDatasetHeader(dataset, data);
    m_Model  = buildClassifier(dataset);
  }

  /**
   * Classifies the specified instance.
   *
   * @param instance	the instance to classify
   * @return		the classification
   * @throws Exception	if classification fails
   */
  @Override
  public double classifyInstance(Instance instance) throws Exception {
    double[]	values;

    values = SmileDatasetUtils.convertInstance(instance, m_Header.getDataset());
    return m_Model.predict(values);
  }

  /**
   * Outputs some information about the model.
   *
   * @return		the model
   */
  @Override
  public String toString() {
    if (m_Model == null)
      return Utils.toCommandLine(this) + "\n" + "No model built yet!";
    else
      return Utils.toCommandLine(this) + "\n" + m_Model.getClass().getName();
  }
}
