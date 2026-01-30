package org.patchbukkit.scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;

public class PatchBukkitScheduler implements BukkitScheduler {

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncDelayedTask'");
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncDelayedTask'");
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncDelayedTask'");
    }

    @Override
    public int scheduleSyncDelayedTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncDelayedTask'");
    }

    @Override
    public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncRepeatingTask'");
    }

    @Override
    public int scheduleSyncRepeatingTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay,
            long period) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSyncRepeatingTask'");
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAsyncDelayedTask'");
    }

    @Override
    public int scheduleAsyncDelayedTask(@NotNull Plugin plugin, @NotNull Runnable task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAsyncDelayedTask'");
    }

    @Override
    public int scheduleAsyncRepeatingTask(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAsyncRepeatingTask'");
    }

    @Override
    public <T> @NotNull Future<T> callSyncMethod(@NotNull Plugin plugin, @NotNull Callable<T> task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'callSyncMethod'");
    }

    @Override
    public void cancelTask(int taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelTask'");
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelTasks'");
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCurrentlyRunning'");
    }

    @Override
    public boolean isQueued(int taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isQueued'");
    }

    @Override
    public @NotNull List<BukkitWorker> getActiveWorkers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveWorkers'");
    }

    @Override
    public @NotNull List<BukkitTask> getPendingTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPendingTasks'");
    }

    @Override
    public @NotNull BukkitTask runTask(@NotNull Plugin plugin, @NotNull Runnable task) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTask'");
    }

    @Override
    public void runTask(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTask'");
    }

    @Override
    public @NotNull BukkitTask runTask(@NotNull Plugin plugin, @NotNull BukkitRunnable task)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTask'");
    }

    @Override
    public @NotNull BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskAsynchronously'");
    }

    @Override
    public void runTaskAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskAsynchronously'");
    }

    @Override
    public @NotNull BukkitTask runTaskAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskAsynchronously'");
    }

    @Override
    public @NotNull BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLater'");
    }

    @Override
    public void runTaskLater(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLater'");
    }

    @Override
    public @NotNull BukkitTask runTaskLater(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLater'");
    }

    @Override
    public @NotNull BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLaterAsynchronously'");
    }

    @Override
    public void runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task,
            long delay) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLaterAsynchronously'");
    }

    @Override
    public @NotNull BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task,
            long delay) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskLaterAsynchronously'");
    }

    @Override
    public @NotNull BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull Runnable task, long delay, long period)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimer'");
    }

    @Override
    public void runTaskTimer(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task, long delay,
            long period) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimer'");
    }

    @Override
    public @NotNull BukkitTask runTaskTimer(@NotNull Plugin plugin, @NotNull BukkitRunnable task, long delay,
            long period) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimer'");
    }

    @Override
    public @NotNull BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Runnable task, long delay,
            long period) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimerAsynchronously'");
    }

    @Override
    public void runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull Consumer<? super BukkitTask> task,
            long delay, long period) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimerAsynchronously'");
    }

    @Override
    public @NotNull BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, @NotNull BukkitRunnable task,
            long delay, long period) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTaskTimerAsynchronously'");
    }

    @Override
    public @NotNull Executor getMainThreadExecutor(@NotNull Plugin plugin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMainThreadExecutor'");
    }
    
}
