import { useEffect, useState } from 'react'
import { Center, Flex, Grid, Text } from '@chakra-ui/react'

import SuGrid from './components/SuGrid';
import Square from './components/Square';
import Section from './components/Section';

function App() {
  const debug = true;

  const isPortrait = window.innerHeight > window.innerWidth;

  const [useSC, setSC] = useState(null);

  const [useMode, setMode] = useState(true);

  const emptyGrid = Array.from({ length: 9 }, (_, X) =>
    Array.from({ length: 9 }, (_, Y) => ({
      value: '',
      position: [X, Y],
      selected: false,
    }))
  );

  const [useGrid, setGrid] = useState(emptyGrid);

  const [useRot, setRot] = useState(isPortrait);

  const clearGrid = () => {
    setSC(null);
    setGrid(emptyGrid);
  }

  const setValue = (x, y, label, value) => {
    setGrid(prev => {
      const copy = [...prev];

      copy[x] = [...copy[x]];
      copy[x][y] = {
        ...copy[x][y],
        [label]: value
      };

      if (debug) console.log(copy)

      return copy;
    });
  };

  const clearSelection = () => {
    setValue(useSC[0], useSC[1], 'selected', false);
    setSC(null);
  }

  const tools = [
    { value: 'Edi', onClick: () => setMode(true), },
    { value: 'Mar', onClick: () => setMode(false), },
    { value: 'Era', onClick: () => setValue(useSC[0], useSC[1], 'value', ''), },
    { value: 'Cle', onClick: clearGrid, },
  ];

  const buttons = Array.from({ length: 9 }, (_, ind) => ({
    value: (1 + ind),
    onClick: () => setValue(useSC[0], useSC[1], 'value', (1 + ind)),
  }));

  useEffect(() => {
    setRot(!useRot);
  }, [isPortrait]);

  return (
    <Center w={'100%'} h={'100vh'} flexDir={useRot ? 'row' : 'column'} gap={{ base: 5, smToMd: '1rem', md: '2rem' }}>

      <Text fontWeight={'medium'} fontSize={'5xl'}>Sudoku</Text>
      <SuGrid >
        {
          useGrid?.map((rows, ind) => {
            return rows?.map((cell, ind) => {
              return (<Square backgroundColor={cell['selected'] ? 'white' : 'red'} key={'posInd' + ind} onClick={() => {
                if (debug) console.log('Cell (clicked) possition: (X,Y) ' + useSC?.[0] + ',' + useSC?.[1]);

                if (!cell['selected'] && useSC == null) {
                  setSC(null);
                  setSC([...cell['position']]);
                  setValue(cell['position'][0], cell['position'][1], 'selected', true);
                }
                else if (!cell['selected'] && !!useSC && (useSC[0] !== cell['position'][0] || useSC[1] !== cell['position'][1])) {
                  clearSelection();
                  setSC([...cell['position']]);
                  setValue(cell['position'][0], cell['position'][1], 'selected', true);
                }
                else if (!!cell['selected'] && !!useSC && useSC[0] == cell['position'][0] && useSC[1] == cell['position'][1]) {
                  setValue(cell['position'][0], cell['position'][1], 'selected', false);
                  setSC(null);
                }

                if (debug) {
                  console.log('Cell (clicked) selection state: ' + cell['selected']);
                  console.log('Cell selected right now: (X,Y) ' + useSC);
                  console.log('Cell (clicked) possition: (X,Y) ' + useSC[0] + ',' + useSC[1]);
                }
              }}>{cell['value']}</Square>)
            })
          })
        }
      </SuGrid>
      <Flex flexDir={useRot ? 'row' : 'column'} bg={'blue'} borderRadius={'xl'} p={{ base: 2, smToMd: 2, md: 3 }}>

        <Section rotation={useRot}>{
          buttons?.map((item, ind) => {
            return (<Square key={'buttInd' + ind} onClick={() => item['onClick']()}>{item['value']}</Square>)
          })
        }
        </Section>
        <Section rotation={useRot} mt={useRot ? '' : { base: 2, smToMd: 2, md: 3 }} ms={useRot ? { base: 2, smToMd: 2, md: 3 } : ''}>{
          tools?.map((item, ind) => {
            return (<Square key={'toolButtInd' + ind} onClick={() => item['onClick']()}>{item['value']}</Square>)
          })
        }
        </Section>

      </Flex>

    </Center >
  )
}

export default App
