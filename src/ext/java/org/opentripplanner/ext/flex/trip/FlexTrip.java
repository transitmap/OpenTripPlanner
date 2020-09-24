package org.opentripplanner.ext.flex.trip;

import org.opentripplanner.ext.flex.distancecalculator.DistanceCalculator;
import org.opentripplanner.ext.flex.template.FlexAccessTemplate;
import org.opentripplanner.ext.flex.template.FlexEgressTemplate;
import org.opentripplanner.model.FeedScopedId;
import org.opentripplanner.model.StopLocation;
import org.opentripplanner.model.TransitEntity;
import org.opentripplanner.model.Trip;
import org.opentripplanner.model.calendar.ServiceDate;
import org.opentripplanner.routing.graphfinder.NearbyStop;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * This class represents the different variations of what is considered flexible transit, and its
 * subclasses encapsulates the different business logic, which the different types of services
 * adhere to.
 */
public abstract class FlexTrip extends TransitEntity<FeedScopedId> {

  protected final Trip trip;

  public FlexTrip(Trip trip) {
    this.trip = trip;
  }

  public abstract Stream<FlexAccessTemplate> getFlexAccessTemplates(
      NearbyStop access, int differenceFromStartOfTime, ServiceDate serviceDate, DistanceCalculator calculator
  );

  public abstract Stream<FlexEgressTemplate> getFlexEgressTemplates(
      NearbyStop egress, int differenceFromStartOfTime, ServiceDate serviceDate, DistanceCalculator calculator
  );

  public abstract int earliestDepartureTime(int departureTime, int fromStopIndex, int toStopIndex, int flexTime);

  public abstract int latestArrivalTime(int arrivalTime, int fromStopIndex, int toStopIndex, int flexTime);

  public abstract Collection<StopLocation> getStops();

  @Override
  public FeedScopedId getId() {
    return trip.getId();
  }

  public Trip getTrip() {
    return trip;
  }
}
