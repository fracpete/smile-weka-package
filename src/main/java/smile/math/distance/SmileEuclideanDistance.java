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
 * SmileEuclideanDistance.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.math.distance;

/**
 * Euclidean distance.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileEuclideanDistance
  extends AbstractSmileDistance<EuclideanDistance> {

  private static final long serialVersionUID = -3657858010202972888L;

  /**
   * Returns a description of the distance.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Euclidean distance.";
  }

  /**
   * Returns a new instance of the distance function.
   *
   * @return		the distance function
   */
  @Override
  public EuclideanDistance getDistance() {
    return new EuclideanDistance();
  }
}
