# Detection of Energy Inefficiencies in Android Wear Watch Faces

This is the artifact for the FSE'18 paper 'Detection of Energy Inefficiencies
in Android Wear Watch Faces' by Hailong Zhang, Haowei Wu and Atanas Rountev.
It includes the static analysis and testing framework, experimental subjects,
materials for app market study, and the logs generated by the static analysis.

 

## Authors

Hailong Zhang, Haowei Wu, Atanas Rountev

## Contributor 

Hailong Zhang

## Email

zhang.4858@osu.edu	

## Notes

This artifact contains the static analysis, testing framework, experimental subjects, materials for app market study, logs generated by the static analysis, and the result of testing. Details on the instructions and usage are in the readme files in each sub-folder.	Available	The artifact is functional and provided to the public with detailed instructions.

## Download from

```
wget http://web.cse.ohio-state.edu/presto/software/aptwear/downloads/fse18.tar.xz
tar xvfz fse18.tar.xz
```
 
 
## Structure

- `analysis` includes the code for static analysis;
- `analysis-logs` contains the log files by running the static analysis against
the 1490 experimental subjects;
- `apks` includes all the experimental subjects, i.e., the APK files for 1490
watch faces, as well as the tool to download them;
- `app-market-study` contains the crawler for app markets and the scripts to
generate statistics in the paper;
- `INSTALL` includes the installation instructions;
- `LICENSE` includes the copyright information;
- `README` is this file;
- `testing` contains the testing framework and the results.

Others may use the static analysis to detect inefficiencies for any new watch
face, use the testing framework to validate the reports by static analysis, as
well as utilizing the benchmarks located at `apks` for further studies.

Please refer to the readme files in each sub-directory for more details.

## Questions

If you have any question, please contact the authors of the paper.
