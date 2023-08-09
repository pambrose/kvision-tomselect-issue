default: versioncheck

clean:
	./gradlew clean

build:
	./gradlew build

versioncheck:
	./gradlew dependencyUpdates

yarn-unlock:
	./gradlew kotlinUpgradeYarnLock

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.2.1 --distribution-type=bin
