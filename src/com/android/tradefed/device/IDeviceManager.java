/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.tradefed.device;

/**
 * Interface for managing the set of available devices for testing.
 */
public interface IDeviceManager {

    /**
     * Request a device for testing.
     *
     * @return a {@link ITestDevice} for testing.
     * @throws DeviceNotAvailableException if no device is available.
     */
    public ITestDevice allocateDevice() throws DeviceNotAvailableException;

    /**
     * Return a device to the pool, making it available for testing.
     * <p/>
     * Attempts to return a device that hasn't been previously allocated will be ignored.
     *
     * @param device the {@link ITestDevice} to return to the pool.
     */
    public void freeDevice(ITestDevice device);

    /**
     * Register a listener for devices becoming available for testing.
     * <p/>
     * Only one active listener at at time is supported.
     *
     * @throw IllegalStateException if a listener has already been registered
     */
    public void registerListener(IDeviceListener listener) throws IllegalStateException;

    /**
     * Removes a previously registered {@link IDeviceListener}.
     * <p/>
     * Attempts to remove a listener that hasn't been previously registered will be ignored.
     *
     * @param listener the listener previously added.
     */
    public void removeListener(IDeviceListener listener);

    /**
     * Waits for device to be online.
     *
     * @param device the previously allocated {@link ITestDevice}
     * @param time the maximum time in ms to wait
     *
     * @throws DeviceNotAvailableException if time expires and device is still not online.
     */
    public void waitForDevice(ITestDevice device, long time) throws DeviceNotAvailableException;
}