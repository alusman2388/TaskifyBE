package com.usmantech.taskifyBackend.prayerReminder;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrayerRepo extends JpaRepository<PrayerEntity, Integer> {

//	@Query(value = "SELECT * FROM prayer_times WHERE FORMAT(prayerTime, 'HH:mm:ss') = :time", nativeQuery = true)
//	List<PrayerEntity> findByPrayerTime(@Param("time") String time);
//	@Query(value = "SELECT * FROM prayer_times WHERE CONVERT(VARCHAR, prayerTime, 108) = :time", nativeQuery = true)
//	List<PrayerEntity> findByPrayerTime(@Param("time") String time);
    List<PrayerEntity> findByPrayerTime(LocalTime prayerTime);



}
