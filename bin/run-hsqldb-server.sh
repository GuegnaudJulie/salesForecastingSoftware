mvn dependency:copy-dependencies
cd data
java -cp '../target/dependency/*' org.hsqldb.Server
