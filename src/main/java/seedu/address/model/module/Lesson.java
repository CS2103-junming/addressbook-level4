package seedu.address.model.module;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.Lecturer.Lecturer;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Lesson implements ReadOnlyLesson{
    private ObjectProperty<ClassType> classType;
    private ObjectProperty<Group> group;
    private ObjectProperty<Location> location;
    private ObjectProperty<TimeSlot> timeSlot;

    private ObjectProperty<Lecturer> lecturer;

    /**
     * Every field must be present and not null.
     */
    public Lesson(ClassType classType, Location location, Group group, TimeSlot timeSlot, Lecturer lecturer) {
        requireAllNonNull(classType, location, group, timeSlot, lecturer);
        this.classType = new SimpleObjectProperty<>(classType);
        this.location = new SimpleObjectProperty<>(location);
        this.group = new SimpleObjectProperty<>(group);
        this.timeSlot = new SimpleObjectProperty<>(timeSlot);
        // protect internal tags from changes in the arg list
        this.lecturer = new SimpleObjectProperty<>(lecturer);
    }

    /**
     * Creates a copy of the given ReadOnlyLesson.
     */
    public Lesson(ReadOnlyLesson source) {
        this(source.getClassType(), source.getLocation(), source.getGroup(),source.getTimeSlot(),
                source.getLecturer());
    }

    public void setLocation(Location location) {
        this.location.set(requireNonNull(location));
    }

    @Override
    public ObjectProperty<Location> locationProperty() {
        return location;
    }

    @Override
    public Location getLocation() {
        return location.get();
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot.set(requireNonNull(timeSlot));
    }

    @Override
    public ObjectProperty<TimeSlot> timeSlotProperty() {
        return timeSlot;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return timeSlot.get();
    }

    public void setClassType(ClassType classType) {
        this.classType.set(requireNonNull(classType));
    }

    @Override
    public ObjectProperty<ClassType> classTypeProperty() {
        return classType;
    }

    @Override
    public ClassType getClassType() {
        return classType.get();
    }

    public void setGroupType(Group group) {
        this.group.set(requireNonNull(group));
    }

    @Override
    public ObjectProperty<Group> groupProperty() {
        return group;
    }

    @Override
    public Group getGroup() {
        return group.get();
    }

    @Override
    public ObjectProperty<Lecturer> lecturerProperty() {
        return lecturer;
    }

    @Override
    public Lecturer getLecturer() { return lecturer.get(); }

    public void setLecturer(Lecturer lecturer) { this.lecturer.set(requireNonNull(lecturer)); }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyLesson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyLesson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(classType, location, group, timeSlot, lecturer);
    }

    @Override
    public String toString() {
        return getAsText();
    }
}
