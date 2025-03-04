package com.cb.recipe.util;
import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({SnapshotExtension.class})
public class UnitTest {
    private Expect expect;

    public <T> void snapshot(final T toBeSnapShotted) {
        this.expect.serializer("json").toMatchSnapshot(toBeSnapShotted);
    }

    public <T> void snapshot(final String uniqueSnapshotName, final T toBeSnapShotted) {
        this.expect.scenario(uniqueSnapshotName).serializer("json").toMatchSnapshot(toBeSnapShotted);
    }
}
