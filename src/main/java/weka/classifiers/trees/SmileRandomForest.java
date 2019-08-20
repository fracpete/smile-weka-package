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

package weka.classifiers.trees;

import smile.classification.Classifier;
import smile.classification.RandomForest;
import smile.data.AttributeDataset;
import weka.classifiers.AbstractSmileClassifier;

/**
 * SMILE RandomForest.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileRandomForest
  extends AbstractSmileClassifier {

  private static final long serialVersionUID = -6558986110434792292L;

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  public String globalInfo() {
    return "Random forest for classification. Random forest is an ensemble "
      + "classifier that consists of many decision trees and outputs the majority "
      + "vote of individual trees. The method combines bagging idea and the "
      + "random selection of features. ";
  }

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  protected Classifier<double[]> buildClassifier(AttributeDataset data) throws Exception {
    return new RandomForest(data, 100);
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
